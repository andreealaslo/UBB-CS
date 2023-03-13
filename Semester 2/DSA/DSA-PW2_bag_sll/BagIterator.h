//
// Created by User on 4/19/2022.
//

#ifndef DSA_PW2_BAG_SLL_BAGITERATOR_H
#define DSA_PW2_BAG_SLL_BAGITERATOR_H

#endif //DSA_PW2_BAG_SLL_BAGITERATOR_H
#include "Bag.h"

class BagIterator
{
    //DO NOT CHANGE THIS PART
    friend class Bag;

private:
    const Bag& bag;
    Node* current;
    TElem occ;
    BagIterator(const Bag& c);

public:
    void first();
    void next();
    TElem getCurrent() const;
    bool valid() const;
    void jumpForward(int k);
};
