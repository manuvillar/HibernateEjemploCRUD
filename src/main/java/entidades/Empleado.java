package entidades;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_no", columnDefinition = "smallint UNSIGNED not null")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 15)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 15)
    private String apellido;

    @Column(name = "oficio", length = 15)
    private String oficio;

    @Column(name = "fecha_alt")
    private LocalDate fechaAlt;

    @Column(name = "salario", precision = 8, scale = 2)
    private BigDecimal salario;

    @Column(name = "comision", precision = 8, scale = 2)
    private BigDecimal comision;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dept_no", nullable = false)
    private Departamento deptNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public LocalDate getFechaAlt() {
        return fechaAlt;
    }

    public void setFechaAlt(LocalDate fechaAlt) {
        this.fechaAlt = fechaAlt;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public BigDecimal getComision() {
        return comision;
    }

    public void setComision(BigDecimal comision) {
        this.comision = comision;
    }

    public Departamento getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Departamento deptNo) {
        this.deptNo = deptNo;
    }

}