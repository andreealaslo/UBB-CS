#include "Map.h"
#include "MapIterator.h"
#include <exception>
using namespace std;


MapIterator::MapIterator(const Map& d) : map(d)
{
    this->current = map.head;
}


void MapIterator::first() {
    this->current = map.head;
}


void MapIterator::next() {
    if(!this->valid())
        throw exception();
    this->current = map.next[current];
}


TElem MapIterator::getCurrent() {
    if (!this->valid())
        throw exception();
    return map.elements[current];
}


bool MapIterator::valid() const {
    return this->current != -1;
}

void MapIterator::jumpBackward(int k) {
     if(!this->valid() || k<=0)
         throw exception();
     int currentKey = this->getCurrent().first;
     int currentPos = 0;
     int traversalNode = map.head;
     while(map.elements[traversalNode].first != currentKey){
         currentPos++;
         traversalNode = map.next[traversalNode];
     }
     int newPos = currentPos - k;
     if(newPos < 0){
         this->current = -1;
         return;
     }
     else{
         traversalNode = map.head;
         currentPos = 0;
         while(currentPos < newPos){
             traversalNode = map.next[traversalNode];
             currentPos ++;
         }
          this->current = traversalNode;
         return;
     }
}






