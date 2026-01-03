package dev.joshlessard.portfolio.adapter.out.web.questrade;

record PositionDto(
    String symbol,
    Integer symbolId,
    Double openQuantity,
    Double closedQuantity,
    Double currentMarketValue,
    Double currentPrice,
    Double averageEntryPrice,
    Double closedPnL,
    Double openPnL,
    Double totalCost,
    Boolean isRealTime,
    Boolean isUnderReorg
) {
}
