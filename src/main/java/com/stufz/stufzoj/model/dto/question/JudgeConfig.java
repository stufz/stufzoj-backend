package com.stufz.stufzoj.model.dto.question;

import lombok.Data;

@Data
public class JudgeConfig {
    /**
     * 时间限制(ms)
     */
    private Long timeLimit;
    /**
     * 堆栈限制(KB)
     */
    private Long stackLimit;
    /**
     * 内存限制(KB)
     */
    private Long memoryLimit;
}
