package com.example.ahorraya.serviceimpl;

import com.example.ahorraya.dtos.AuthResponseDTO;
import com.example.ahorraya.dtos.LoginDTO;
import com.example.ahorraya.dtos.UsuarioRegistroDTO;
import com.example.ahorraya.dtos.UsuarioResponseDTO;
import com.example.ahorraya.entities.ClienteEntity;
import com.example.ahorraya.entities.EstadoUsuarioEnum;
import com.example.ahorraya.entities.RolEntity;
import com.example.ahorraya.entities.UsuarioEntity;
import com.example.ahorraya.exceptions.RecursoNoEncontradoException;
import com.example.ahorraya.repositories.ClienteRepository;
import com.example.ahorraya.repositories.RolRepository;
import com.example.ahorraya.repositories.UsuarioRepository;
import com.example.ahorraya.security.JwtService;
import com.example.ahorraya.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Override
    public AuthResponseDTO registrar(UsuarioRegistroDTO dto) {
        if (usuarioRepository.findByCorreo(dto.getCorreo()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario con ese correo");
        }

        RolEntity rolCliente = rolRepository.findByNombre("CLIENTE")
                .orElseThrow(() -> new RecursoNoEncontradoException("Rol CLIENTE no existe, revisa el DataLoader"));

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setCorreo(dto.getCorreo());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setFechaCreacion(LocalDate.now());
        usuario.setEstado(EstadoUsuarioEnum.ACTIVO); // ajusta al nombre real de tu constante
        usuario.setRoles(List.of(rolCliente));

        UsuarioEntity usuarioGuardado = usuarioRepository.save(usuario);

        ClienteEntity cliente = new ClienteEntity();
        cliente.setNombres(dto.getNombres());
        cliente.setApellidos(dto.getApellidos());
        cliente.setTelefono(dto.getTelefono());
        cliente.setUsuario(usuarioGuardado);
        clienteRepository.save(cliente);

        return construirAuthResponse(usuarioGuardado);
    }

    @Override
    public AuthResponseDTO login(LoginDTO dto) {
        UsuarioEntity usuario = usuarioRepository.findByCorreo(dto.getCorreo())
                .orElseThrow(() -> new RecursoNoEncontradoException("Correo o contraseña incorrectos"));

        if (!passwordEncoder.matches(dto.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Correo o contraseña incorrectos");
        }

        return construirAuthResponse(usuario);
    }

    @Override
    public UsuarioResponseDTO buscarPorId(Long idUsuario) {
        UsuarioEntity usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado"));
        return convertirADTO(usuario);
    }

    private AuthResponseDTO construirAuthResponse(UsuarioEntity usuario) {
        List<String> roles = usuario.getRoles().stream()
                .map(RolEntity::getNombre)
                .collect(Collectors.toList());

        String token = jwtService.generarToken(usuario.getCorreo(), roles);

        return new AuthResponseDTO(token, usuario.getIdUsuario(), usuario.getCorreo(), roles);
    }

    private UsuarioResponseDTO convertirADTO(UsuarioEntity usuario) {
        List<String> roles = usuario.getRoles().stream()
                .map(RolEntity::getNombre)
                .collect(Collectors.toList());

        return new UsuarioResponseDTO(
                usuario.getIdUsuario(),
                usuario.getCorreo(),
                usuario.getFechaCreacion(),
                usuario.getEstado().toString(),
                roles
        );
    }

}
