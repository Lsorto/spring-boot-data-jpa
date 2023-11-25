package com.bolsadeideas.springboot.app.models.dao;

import com.bolsadeideas.springboot.app.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsarioDao extends CrudRepository<Usuario,Long> {
    public Usuario findByUsername (String username);
}
