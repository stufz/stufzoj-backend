package com.stufz.stufzoj.service;

import com.stufz.stufzoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.stufz.stufzoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stufz.stufzoj.model.entity.User;

/**
* @author 10632
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-04-08 14:28:38
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return 提交记录的id
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);


}
