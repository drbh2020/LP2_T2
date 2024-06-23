package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.entity.AreaEntity;
import com.example.demo.entity.EmpleadoEntity;

@Service
public interface AreaService {
	List<AreaEntity> buscarTodosAreas();
	AreaEntity buscarAreaPorId(Integer id); // el Id
	AreaEntity buscarAreaPorNombre(String nombre); // el Id
	void crearArea(AreaEntity usuarioEntity, Model model);
	void actualizarArea(AreaEntity usuarioEntity, Model model);
	void eliminarArea(Integer id);
}
