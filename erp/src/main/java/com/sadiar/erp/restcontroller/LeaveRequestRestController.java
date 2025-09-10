package com.sadiar.erp.restcontroller;

import com.sadiar.erp.entity.LeaveRequest;
import com.sadiar.erp.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveRequestRestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @GetMapping
    public List<LeaveRequest> getAll() {
        return leaveRequestService.getAllRequests();
    }

    @GetMapping("/{id}")
    public LeaveRequest getById(@PathVariable Long id) {
        return leaveRequestService.getRequestById(id);
    }

    @PostMapping
    public LeaveRequest create(@RequestBody LeaveRequest request) {
        return leaveRequestService.createRequest(request);
    }

    @PutMapping("/{id}/approve")
    public LeaveRequest approve(@PathVariable Long id) {
        return leaveRequestService.approveRequest(id);
    }

    @PutMapping("/{id}/reject")
    public LeaveRequest reject(@PathVariable Long id) {
        return leaveRequestService.rejectRequest(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        leaveRequestService.deleteRequest(id);
    }
}
