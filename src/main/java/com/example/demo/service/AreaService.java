package com.example.demo.service;

import java.util.List;

import org.springframework.ui.Model;

import com.example.demo.entity.AreaEntity;


public interface AreaService {
	List<AreaEntity> buscarTodosAreas();
	AreaEntity buscarAreaPorId(Integer id); // el Id
	void crearArea(AreaEntity areaEntity, Model model);
	void actualizarArea(AreaEntity areaEntity, Model model);
	void eliminarArea(Integer id);
}
