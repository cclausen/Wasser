package de.horroreyes.wasser.repositories;

import de.horroreyes.wasser.model.Rfid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RfidRepository extends JpaRepository<Rfid, String> {
}
