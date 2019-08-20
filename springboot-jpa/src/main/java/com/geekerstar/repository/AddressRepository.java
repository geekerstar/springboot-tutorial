package com.geekerstar.repository;

import com.geekerstar.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author geekerstar
 * date: 2019-08-20 15:33
 * description:
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}
