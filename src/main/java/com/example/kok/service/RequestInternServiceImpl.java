package com.example.kok.service;

import com.example.kok.dto.RequestInternDTO;
import com.example.kok.dto.RequestInternFileDTO;
import com.example.kok.repository.MemberAlarmSettingDAO;
import com.example.kok.repository.RequestInternDAO;
import com.example.kok.repository.RequestInternFileDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestInternServiceImpl implements RequestInternService {
    private final RequestInternDAO requestInternDAO;
    private final RequestInternFileDAO requestInternFileDAO;
    private final MemberAlarmSettingDAO memberAlarmSettingDAO;

    @Override
    public void applyForIntern(RequestInternDTO requestInternDTO) {
        Long memberAlarmSettingId=memberAlarmSettingDAO.findByMemberId(requestInternDTO.getMemberId());
        requestInternDTO.setMemberAlarmSettingId(memberAlarmSettingId);
        requestInternDAO.applyForIntern(requestInternDTO);
        System.out.println(requestInternDTO.getId());
        Long reqId=requestInternDTO.getId();
        Long fileId=requestInternDTO.getFileId();
        RequestInternFileDTO file=new RequestInternFileDTO();
        file.setFileId(fileId);
        file.setRequestInternId(reqId);
        requestInternFileDAO.saveRequestFile(file);
    }
}
