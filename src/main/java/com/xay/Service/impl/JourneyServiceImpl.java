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
    AccountMapper accountMapper;

    @Autowired
    JourneyMapper journeyMapper;

    @Override
    public BaseResult postJourney(JourneyDomain journeyDomain) {
        AccountDO accountDO = accountMapper.getCustomerByUserId(journeyDomain.getCustomerId());
        if (accountDO != null){
            journeyMapper.postJourney(new JourneyDO(journeyDomain));
            return new BaseResult();
        }
        return new BaseResult(500, "用户不存在");
    }

    @Override
    public JourneyDomain[] getJourneyByCustomerId(Integer customerId){
        JourneyDO[] journeyDOS = journeyMapper.getJourneyByCustomerId(customerId);
        JourneyDomain[] journeyDomains = new JourneyDomain[journeyDOS.length];
        for (int i = 0; i < journeyDOS.length; i++){
            journeyDomains[i] = new JourneyDomain(journeyDOS[i]);
        }
        return journeyDomains;
    }

    @Override
    public JourneyDomain getJourneyByJourneyId(Integer journeyId) {
        JourneyDO journeyDO = journeyMapper.getJourneyByJourneyId(journeyId);
        if (journeyDO != null) {
            return new JourneyDomain(journeyDO);
        }
        return new JourneyDomain();
    }

    @Override
    public BaseResult updateJourney(JourneyDomain journeyDomain) {
        JourneyDO journeyDO = journeyMapper.getJourneyByJourneyId(journeyDomain.getJourneyId());
        if (journeyDO != null){
            journeyDO = new JourneyDO(journeyDomain);
            journeyMapper.updateJourney(journeyDO);
            return new BaseResult();
        }
        return new BaseResult(500, "行程不存在");
    }
}
