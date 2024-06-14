package com.green.service;

import org.springframework.ui.Model;

import com.green.domain.dto.FaqSaveDTO;
import com.green.domain.entity.Division;

public interface FaqService {

	void saveProcess(FaqSaveDTO dto);

	void listProcess(int dno, Model model);

	void listProcess();

}
