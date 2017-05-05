package com.xay.Controller;

import com.xay.Domain.BaseResult;
import com.xay.MySQL.Mapper.AccountMapper;
import com.xay.Security.AuthenticationRequest;
import com.xay.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/4.
 */
@CrossOrigin
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 用户注册
     * @param account
     * @return
     */
    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public BaseResult register(@RequestBody WebAccount account){
        return accountService.register(account);
    }

    /**
     * 用户登陆
     * @param authenticationRequest
     * @return
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/sessions/customer", method = RequestMethod.POST)
    public BaseResult authCustomer(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (accountMapper.getCustomerByUsername(authenticationRequest.getUsername()) != null){
            return new BaseResult();
        }else return new BaseResult(500, "用户类型错误");
    }

    @RequestMapping(value = "/sessions/guide", method = RequestMethod.POST)
    public BaseResult authGuide(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (accountMapper.getGuideByUsername(authenticationRequest.getUsername()) != null){
            return new BaseResult();
        }else return new BaseResult(500, "用户类型错误");
    }

    /**
     * 用户修改
     * @param account
     * @return
     */
    @RequestMapping(value = "/accounts", method = RequestMethod.PATCH)
    public BaseResult update(@RequestBody WebAccount account) {
        return accountService.update(account);
    }
}
