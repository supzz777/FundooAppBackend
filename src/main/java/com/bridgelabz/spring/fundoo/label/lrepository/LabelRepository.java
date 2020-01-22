package com.bridgelabz.spring.fundoo.label.lrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bridgelabz.spring.fundoo.label.lmodel.LabelModel;

@Repository
public interface LabelRepository  extends JpaRepository<LabelModel, Object>
{
	

}
