
using namespace std;
#include "Map.h"
#include "MapIterator.h"

Map::Map() {
    this->nrElements = 0;
    this->capacity = 10;
    this->elements = new TElem[capacity];
    this->next = new int[capacity];
    this->head = -1;
    for (int i = 0; i < this->capacity - 1; ++i) {
        this->next[i] = i + 1;
    }
    this->next[this->capacity - 1] = -1;
    this->firstEmpty = 0;
}//Theta(n)

void Map::resize() {
    auto *newElementArray = new TElem[this->capacity * 2];
    int *newNext = new int[this->capacity * 2];

    for (int i = 0; i < this->capacity; ++i) {
        newElementArray[i] = this->elements[i];
        newNext[i] = this->next[i];
    }
    newNext[firstEmpty] = this->capacity;
    for (int i = this->capacity; i < this->capacity * 2 - 1; ++i) {
        newNext[i] = i + 1;
    }
    newNext[this->capacity * 2 - 1] = -1;

    TElem *oldElements = this->elements;
    int *oldNext = this->next;

    this->elements = newElementArray;
    this->next = newNext;
    this->capacity *= 2;

    delete oldElements;
    delete oldNext;
}//O(n)

Map::~Map() {
    delete[] this->elements;
    delete[] this->next;
}

TValue Map::add(TKey c, TValue v) {
    if (this->next[this->firstEmpty] == -1)
        resize();

    int current1 = this->head;
    int current2 = this->head;

    while (current2 != -1) {
        if (this->elements[current2].first == c) {
            // update value at existent key
            TValue oldValue = this->elements[current2].second;
            this->elements[current2].second = v;
            return oldValue;
        }
        current1 = current2;
        current2 = this->next[current2];
    }
    //current 1 va ajunge la ultimul element din lista si current 2 este -1
    // key not found
    int nextOfFirstEmpty = this->next[this->firstEmpty];
    this->elements[this->firstEmpty] = pair<TKey, TValue>(c,v); // ANY CASE: put the value on the first available position
    this->next[this->firstEmpty] = current2; //facem ca next-ul ultimului element adaugat sa fie -1
    if (current1 != -1 && current1 != current2) { // MIDDLE CASE: update the previous element (if we dont have to manage the head)
        this->next[current1] = this->firstEmpty;
    }
    if (current2 == this->head) { // HEAD CASE: update the head
        this->head = this->firstEmpty;
    }
    this->firstEmpty = nextOfFirstEmpty;
    this->nrElements++;
    return NULL_TVALUE;
}

TValue Map::search(TKey c) const{
    int current = this->head;
    while(current != -1 && this->elements[current].first != c){
        current = this->next[current];
    }
    if(current == -1)
        return NULL_TVALUE;

    return elements[current].second;
}

TValue Map::remove(TKey c) {
    int current1 = this->head;
    int current2 = this->head;

    while (current2 != -1 && this->elements[current2].first != c) {
        current1 = current2;
        current2 = this->next[current2];
    }
    if (current2 == -1) { // non-existent element or empty map
        return NULL_TVALUE;
    }

    if (current2 == this->head) {
        this->head = this->next[current2];//remove la head
    }

    this->next[current1] = this->next[current2];
    this->next[current2] = this->firstEmpty;
    this->firstEmpty = current2;
    this->nrElements -= 1;

    return this->elements[current2].second;
}


int Map::size() const {
    return this->nrElements;
}

bool Map::isEmpty() const{
    return this->nrElements == 0;
}

MapIterator Map::iterator() const {
    return MapIterator(*this);
}



