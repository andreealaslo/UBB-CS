#pragma once

#include <iostream>
#include "SortedMultiMap.h"


class SMMIterator{
    friend class SortedMultiMap;
private:
    //DO NOT CHANGE THIS PART
    SortedMultiMap& map;
    Node* head;
    Node* currentNode;
    // prevNode;
    explicit SMMIterator(SortedMultiMap& map);

    Node *SortedMerge(Node *a, Node *b);

    Node *mergeKLists(Node **arr, int last);

    Node *deepCopyList(Node *head);

public:
    void first();
    void next();
    bool valid() const;
    TElem getCurrent() const;

};

