from nltk import *
#from nltk.book import *
from nltk.corpus import PlaintextCorpusReader
import sys
from numpy import *

def similarwords(word):
    oldstdout = sys.stdout
    sys.stdout = open("out.txt", "w+")
    
    moby = Text(corpus.gutenberg.words('melville-moby_dick.txt')) #CORPORA ON THIS LINE
    t = moby.similar(word) #GET SIMILAR WORDS FROM CORPORA
    
    sys.stdout = oldstdout
    
    txt = open('out.txt', 'r')
    similarwords =  word_tokenize(txt.read())
        
 #   print(similarwords)
    
    random_upper_treshold = len(similarwords)
    random_similarwords = [0,0,0]
    random_numbers = [0,0,0]
    random_numbers[0] = random.randint(0, random_upper_treshold) #firsta random vardet
    
    while True: #ser till att andra random vardet inte ar samma som forsta
        secondrandom = random.randint(0, random_upper_treshold)
        if(secondrandom != random_numbers[0]):
            random_numbers[1] = secondrandom
            break

    while True: #ser till att tredje random inte ar som forsta och andra
        thirdrandom = random.randint(0, random_upper_treshold)
        if(thirdrandom != random_numbers[0]):
            if(thirdrandom != random_numbers[1]): #fostod in and i python gjorde tva if satser istallet
                random_numbers[2] = thirdrandom
                break
    
    for x in range (0, 3): #stoppa in de tre random similar orden i en lista
        random_similarwords[x] = similarwords[random_numbers[x]]
#    print(random_similarwords)
    return random_similarwords

def main():

    similarwords("captain")
    


if __name__ == "__main__":
    main()
    



