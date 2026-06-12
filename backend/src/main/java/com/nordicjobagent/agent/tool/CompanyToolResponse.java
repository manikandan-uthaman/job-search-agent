package com.nordicjobagent.agent.tool;

public record CompanyToolResponse(

        String name,

        String country,

        boolean hiring,

        int score

) {
}
