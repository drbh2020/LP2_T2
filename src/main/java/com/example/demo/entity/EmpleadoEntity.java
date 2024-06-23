package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "tb_empleado")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmpleadoEntity {
	
	@Id
	@Column(name = "dni_empleado", length = 8, nullable = false, columnDefinition = "CHAR(8)")
	private String dni;
	
	@Column(name = "nombre_empleado", length = 45, nullable = false)
	private String nombre;
	
	@Column(name = "apellido_empleado", length = 45, nullable = false)
	private String apellido;
	
	@Column(name = "fecha_nacimiento", updatable = false, nullable = false)
	@Temporal(TemporalType.DATE)
	private LocalDate fechaNacimiento;
	
	@Column(name = "direccion", length = 45, nullable = false)
	private String direccion;
	
	@Column(name = "correo", length = 45, unique = true, nullable = false )
	private String correo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "area_id", nullable = false)
	private AreaEntity areaEntity;
}
