package com.myprojects.queues;

import com.myprojects.lists.ListNode;

public record QueueStatus(
    ListNode newHead,
    int removedValue
) {}
