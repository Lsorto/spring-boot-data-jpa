package com.bolsadeideas.springboot.app.models.service;

import ch.qos.logback.classic.Logger;
import com.bolsadeideas.springboot.app.models.dao.IUsarioDao;
import com.bolsadeideas.springboot.app.models.entity.Role;
import com.bolsadeideas.springboot.app.models.entity.Usuario;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private IUsarioDao usarioDao;
    private Logger logger = (Logger) LoggerFactory.getLogger(JpaUserDetailsService.class);
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usarioDao.findByUsername(username);

        if(usuario == null){
            logger.error("Error en el login: no existe el usuario "+username+ " En el sistema");
            throw new UsernameNotFoundException("Username: "+username+
                    " no existe en el sistema!");
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(Role role:usuario.getRoles()){
            logger.info("Role: ".concat(role.getAuthority()));
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        if (authorities.isEmpty()){
            logger.error("Erro en el login : Usuario "+username+" ."+
                    "no tiene role asignados!");
            throw new UsernameNotFoundException("Error en el login: usuario "
            + username + " no tiene roles asignados");
        }
        return new User(usuario.getUsername(),usuario.getPassword(),
                usuario.getEnable(),true,true,true,authorities);
    }
}
