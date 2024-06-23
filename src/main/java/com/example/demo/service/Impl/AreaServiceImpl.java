package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.entity.AreaEntity;
import com.example.demo.entity.EmpleadoEntity;
import com.example.demo.repository.AreaRepository;
import com.example.demo.service.AreaService;
import com.example.demo.utils.Utilitarios;

@Service
public class AreaServiceImpl implements AreaService{

	@Autowired
	private AreaRepository areaRepository;
	
	@Override
	public List<AreaEntity> buscarTodosAreas() {
		return areaRepository.findAll();
	}

	@Override
	public AreaEntity buscarAreaPorId(Integer id) {
		return areaRepository.findById(id).get();
	}

	@Override
	public void crearArea(AreaEntity areaEntity, Model model) {
		areaRepository.save(areaEntity);
		
		model.addAttribute("registroCorrecto", "Registro Correcto");
		model.addAttribute("area", new AreaEntity());
	}

	@Override
	public void actualizarArea(AreaEntity areaEntity, Model model) {
		areaRepository.save(areaEntity);
		
		model.addAttribute("ActualizacionCorrecto", "Actualizacion Correcto");
		
	}

	@Override
	public void eliminarArea(Integer id) {
		areaRepository.deleteById(id);
	}
	
}
