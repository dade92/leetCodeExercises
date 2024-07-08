package com.myprojects.stacks;

import com.myprojects.lists.ListNode;

public record StackStatus(
    ListNode newHead,
    int removedValue
) {}
