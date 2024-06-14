package com.green.service;

import org.springframework.ui.Model;

import com.green.domain.dto.NoticeSaveDTO;
import com.green.domain.dto.NoticeUpdateDTO;

public interface NoticeService {

	void listProcess(int division, Model model);

	void saveProcess(NoticeSaveDTO dto);

	void detailProcess(long no, Model model);

	void deleteProcess(long no);

	void updateProcess(long no, NoticeUpdateDTO dto);

}
