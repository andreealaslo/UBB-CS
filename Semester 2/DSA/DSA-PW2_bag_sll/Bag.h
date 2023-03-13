//
// Created by User on 4/19/2022.
//

#ifndef DSA_PW2_BAG_SLL_BAG_H
#define DSA_PW2_BAG_SLL_BAG_H

#endif //DSA_PW2_BAG_SLL_BAG_H


#pragma once
#include<utility>
//DO NOT INCLUDE BAGITERATOR


//DO NOT CHANGE THIS PART
#define NULL_TELEM -111111;
typedef int TElem;
typedef std::pair<TElem, TElem > Pair;

class Node{
private:
    Pair data;
    Node* next;
public:
    Node(const Pair& _data, Node* _next):data(_data), next(_next) {}
    Pair& getData() {return data;}
    Node* getNext() {return next;}
    void setNext(Node* _next) {next = _next;}
};

class BagIterator;
class Bag {
private:
    Node* head;
    int nr_pairs;

    //DO NOT CHANGE THIS PART
    friend class BagIterator;
public:
    //constructor
    Bag();

    //adds an element to the bag
    void add(TElem e);

    //removes one occurence of an element from a bag
    //returns true if an element was removed, false otherwise (if e was not part of the bag)
    bool remove(TElem e);

    //checks if an element appears is the bag
    bool search(TElem e) const;

    //returns the number of occurrences for an element in the bag
    int nrOccurrences(TElem e) const;

    //returns the number of elements from the bag
    int size() const;

    //returns an iterator for this bag
    BagIterator iterator() const;

    //checks if the bag is empty
    bool isEmpty() const;

    //destructor
    ~Bag();
};