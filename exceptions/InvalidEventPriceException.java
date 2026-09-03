package com.ms.yes_no_treading_application.exceptions;

import lombok.Getter;

@Getter
public class InvalidEventPriceException extends RuntimeException {
  private final Double yesPrice;
  private final Double noPrice;
  private final int StatusCode;

  public InvalidEventPriceException(Double yesPrice, Double noPrice) {
    super("yesPrice and noPrice total should be 10");
      this.yesPrice = yesPrice;
      this.noPrice = noPrice;
      this.StatusCode=400;
  }
}
