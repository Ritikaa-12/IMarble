package com.marble.repos;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.marble.entities.StaffAttendance;

public interface StaffAttendanceRepository extends JpaRepository<StaffAttendance, Integer> {

    Optional<StaffAttendance> findByStaff_StaffId(Integer staffId);
    List<StaffAttendance> findAllByStaff_StaffId(Integer staffId);
}

