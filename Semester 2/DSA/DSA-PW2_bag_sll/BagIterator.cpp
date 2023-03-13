//
// Created by User on 4/19/2022.
//

#include "BagIterator.h"
#include <exception>
#include "Bag.h"
using namespace std;

//Theta(1)
BagIterator::BagIterator(const Bag& c): bag(c)
{
    this->current = c.head;
    this->occ = 1;
}

//Theta(1)
void BagIterator::first() {
    this->current = bag.head;
    this->occ = 1;
}

//Theta(1)
void BagIterator::next() {
    if (current == nullptr)
        throw exception();
    else
    {
        if (this->occ >= current->getData().second)
        {
            current = current->getNext();
            this->occ = 1;
        }
        else
            this->occ++;
    }
}

//Theta(1)
bool BagIterator::valid() const {
    return current != nullptr;
}


//Theta(1)
TElem BagIterator::getCurrent() const
{

    if (this->current != nullptr)
        return current->getData().first;
    else
        throw exception();
}

//Theta(k)
void BagIterator::jumpForward(int k){

    int w = 0;
    if (k <= 0)
        throw exception();
    if (current == nullptr)
        throw exception();
    else
    {
        while(this->current != nullptr && w < k) {
            if (this->occ >= current->getData().second) {
                current = current->getNext();
                this->occ = 1;

            } else
                this->occ++;
            w++;
        }
    }
    if (w < k)
        throw exception();
}