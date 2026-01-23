package com.marble.enums;

public enum StockTransactionType {
    PURCHASE,  // Stock in
    SELLS,     // Stock out
    RETURN,    // Stock in (from customer return)
    DAMAGE,    // Stock out (marked as damaged)
    MISSING    // Stock out (marked as missing)
}