package cf.equipes_service.Repository;

import cf.equipes_service.Entity.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EquipRepository extends JpaRepository<Equipe, Long> {
}
