package snap.api.message.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.domains.plan.service.PlanDomainService;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final PlanDomainService planDomainService;


}
