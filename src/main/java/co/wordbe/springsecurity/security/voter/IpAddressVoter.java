package co.wordbe.springsecurity.security.voter;

import co.wordbe.springsecurity.service.SecurityResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class IpAddressVoter implements AccessDecisionVoter {

    private final SecurityResourceService securityResourceService;

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class aClass) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection collection) {
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String remoteAddress = details.getRemoteAddress();

        List<String> accessIpList = securityResourceService.getAccessIpList();

        for (String ipAddress: accessIpList) {
            if (remoteAddress.equals(ipAddress)) {
                // NOT ACCESS_GRANTED (다음 Voter 로 넘긴다)
                return ACCESS_ABSTAIN;
            }
        }

        // ACCESS_DENIED
        throw new AccessDeniedException("Invalid IP Address");
    }

}
