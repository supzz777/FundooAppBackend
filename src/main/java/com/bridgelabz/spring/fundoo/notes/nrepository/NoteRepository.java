package com.bridgelabz.spring.fundoo.notes.nrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bridgelabz.spring.fundoo.notes.nmodel.NoteModel;

@Repository
public interface NoteRepository extends JpaRepository<NoteModel, Object> {

	NoteModel findByTitle(String title);

	// NoteModel findById(int id);

	// Optional<NoteModel> findById(int id);

}
