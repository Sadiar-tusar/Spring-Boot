package com.sadiar.erp.service;

import com.sadiar.erp.entity.LeaveRequest;
import com.sadiar.erp.entity.LeaveStatus;
import com.sadiar.erp.repository.ILeaveRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestService {

    @Autowired
    private ILeaveRequestRepo leaveRequestRepository;

    public List<LeaveRequest> getAllRequests() {
        return leaveRequestRepository.findAll();
    }

    public LeaveRequest getRequestById(Long id) {
        return leaveRequestRepository.findById(id).orElse(null);
    }

    public LeaveRequest createRequest(LeaveRequest request) {
        return leaveRequestRepository.save(request);
    }

    public LeaveRequest approveRequest(Long id) {
        LeaveRequest req = getRequestById(id);
        if (req != null) {
            req.setStatus(LeaveStatus.APPROVED);
            return leaveRequestRepository.save(req);
        }
        return null;
    }

    public LeaveRequest rejectRequest(Long id) {
        LeaveRequest req = getRequestById(id);
        if (req != null) {
            req.setStatus(LeaveStatus.REJECTED);
            return leaveRequestRepository.save(req);
        }
        return null;
    }

    public void deleteRequest(Long id) {
        leaveRequestRepository.deleteById(id);
    }
}
