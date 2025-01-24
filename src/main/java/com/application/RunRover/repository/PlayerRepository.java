package com.application.RunRover.repository;

import com.application.RunRover.model.Player;
import com.application.RunRover.model.enums.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player,Integer> {

    List<Player> findByAgeGreaterThanEqual(int age);

    @Query(value = "select p from Player p where p.age >= :age and p.speciality = :speciality")
    List<Player> getAllByAgeGreaterThanAndSpeciality(int age,
                                                     Speciality speciality);

    @Query("SELECT p FROM Player p JOIN p.profile prof WHERE prof.runsScored = (SELECT MAX(pp.runsScored) FROM PlayerProfile pp)")
    Optional<Player> findPlayerWithHighestRunsScored();
}
