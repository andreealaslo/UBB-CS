//
// Created by User on 4/19/2022.
//

#include "Bag.h"
#include "BagIterator.h"
#include <exception>
using namespace std;


Bag::Bag() {
    this->head = nullptr;
    this->nr_pairs = 0;
}

//Theta(n)
void Bag::add(TElem elem) {

    auto parsingNode = head;
    if(parsingNode != nullptr)
        while (parsingNode->getNext() != nullptr) {
            if(parsingNode->getData().first == elem)
            {
                parsingNode->getData().second++;
                return;
            }
            parsingNode = parsingNode->getNext();
        }
    //for the last node
    if(parsingNode != nullptr)
        if(parsingNode->getData().first == elem)
        {
            parsingNode->getData().second++;
            return;
        }

    Pair newelem;
    newelem.first = elem;
    newelem.second = 1;
    auto auxNode = new Node(newelem, nullptr);
    if(parsingNode == nullptr)
        head = auxNode;
    else
        parsingNode->setNext(auxNode);
    this->nr_pairs++;
}


//Theta(n)
bool Bag::remove(TElem elem) {
    auto parsingNode = head;

    if(parsingNode != nullptr)
        if (parsingNode->getData().first == elem) {
            parsingNode->getData().second--;
            if (parsingNode->getData().second == 0) {
                this->nr_pairs--;
                head = head->getNext();
            }
            return true;
        }


    if(parsingNode != nullptr)
        while (parsingNode->getNext() != nullptr) {
            if (parsingNode->getNext()->getData().first == elem) {
                parsingNode->getNext()->getData().second--;
                if (parsingNode->getNext()->getData().second == 0) {
                    this->nr_pairs--;

                    parsingNode->setNext(parsingNode->getNext()->getNext());}
                return true;
            }
            parsingNode = parsingNode->getNext();
        }
    return false;
}


//Theta(n)
bool Bag::search(TElem elem) const {

    auto parsingNode = head;
    if(parsingNode != nullptr)
        while (parsingNode != nullptr) {
            if(parsingNode->getData().first == elem)
                return true;
            parsingNode = parsingNode->getNext();
        }
    return false;
}


//Theta(n)
int Bag::nrOccurrences(TElem elem) const {

    auto parsingNode = head;
    if(parsingNode != nullptr)
        while (parsingNode != nullptr){
            if(parsingNode->getData().first == elem)
                return parsingNode->getData().second;
            parsingNode = parsingNode->getNext();
        }
    return 0;
}


//Theta(n)
int Bag::size() const {
    int s = 0;
    auto parsingNode = head;

    while (parsingNode != nullptr) {
        s = s + parsingNode->getData().second;
        parsingNode = parsingNode->getNext();
    }
    return s;
}


//Theta(1)
bool Bag::isEmpty() const {
    if(head == nullptr)
        return true;
    return 0;
}

//Theta(1)
BagIterator Bag::iterator() const {
    return BagIterator(*this);
}

//Theta(n)
Bag::~Bag() {
    Node* curr_elem;
    while (head != nullptr) {
        curr_elem = head;
        head = head->getNext();
        delete curr_elem;
    }
}