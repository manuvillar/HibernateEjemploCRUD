import entidades.Departamento;
import entidades.Empleado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class EmpleadoDAO {
    private static final EntityManager entityManager =
            Persistence.createEntityManagerFactory("default").createEntityManager();

    // Create (Insert)
    public void create(Empleado empleado) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(empleado);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Read (Select por ID)
    public Empleado read(Integer id) {
        return entityManager.find(Empleado.class, id);
    }

    // Read (Select de todos)
    public List<Empleado> readAll() {
        return entityManager.createQuery("SELECT e FROM Empleado e", Empleado.class).getResultList();
    }

    // Update
    public void update(Empleado empleado) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(empleado);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Delete
    public void delete(Integer id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Empleado empleado = entityManager.find(Empleado.class, id);
            if (empleado != null) {
                entityManager.remove(empleado);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Departamento obtenerDepartamento(short deptNo) {
        return entityManager.find(Departamento.class, deptNo);
    }

}

