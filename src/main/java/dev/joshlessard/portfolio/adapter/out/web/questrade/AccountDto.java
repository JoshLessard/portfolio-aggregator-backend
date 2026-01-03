package dev.joshlessard.portfolio.adapter.out.web.questrade;

record AccountDto(
    String type,
    Integer number,
    String status,
    Boolean isPrimary,
    Boolean isBilling,
    String clientAccountType
) {
}
