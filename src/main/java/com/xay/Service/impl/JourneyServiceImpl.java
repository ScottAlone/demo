package com.xay.Service.impl;

import com.xay.Domain.BaseResult;
import com.xay.Domain.JourneyDomain;
import com.xay.MySQL.DO.AccountDO;
import com.xay.MySQL.DO.JourneyDO;
import com.xay.MySQL.Mapper.AccountMapper;
import com.xay.MySQL.Mapper.JourneyMapper;
import com.xay.Service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/8.
 */
@Service
public class JourneyServiceImpl implements JourneyService{

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private JourneyMapper journeyMapper;

    @Override
    public BaseResult<Object> postJourney(JourneyDomain journeyDomain) {
        AccountDO accountDO = accountMapper.getCustomerByUsername(journeyDomain.getcUsername());
        if (accountDO != null){
            journeyMapper.postJourney(new JourneyDO(journeyDomain));
            return new BaseResult<>();
        }
        return new BaseResult<>(500, "用户不存在");
    }

    @Override
    public BaseResult<Object> getJourneyByUsername(String username){
        JourneyDO[] journeyDOS = journeyMapper.getJourneyByUsername(username);
        JourneyDomain[] journeyDomains = new JourneyDomain[journeyDOS.length];
        if (journeyDomains.length == 0){
            return new BaseResult<>(500, "没有行程");
        }
        for (int i = 0; i < journeyDOS.length; i++){
            journeyDomains[i] = new JourneyDomain(journeyDOS[i]);
        }
        return new BaseResult<>(journeyDomains);
    }

    @Override
    public BaseResult<Object> getJourneyByJourneyId(Integer journeyId) {
        JourneyDO journeyDO = journeyMapper.getJourneyByJourneyId(journeyId);
        if (journeyDO != null) {
            return new BaseResult<>(new JourneyDomain(journeyDO));
        }
        return new BaseResult<>(500, "行程不存在");
    }

    @Override
    public BaseResult<Object> updateJourney(JourneyDomain journeyDomain) {
        JourneyDO journeyDO = journeyMapper.getJourneyByJourneyId(journeyDomain.getJourneyId());
        if (journeyDO != null){
            journeyDO = new JourneyDO(journeyDomain);
            journeyMapper.updateJourney(journeyDO);
            return new BaseResult<>();
        }
        return new BaseResult<>(500, "行程不存在");
    }
}
