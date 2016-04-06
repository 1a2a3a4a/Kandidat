from nltk import *
from numpy import *

def Tagging(text):
   sentences = tokenize.sent_tokenize(text)
   print(sentences)
   tokens = [tokenize.word_tokenize(s) for s in sentences]
   print(tokens)
   PosTokens = [pos_tag(e) for e in tokens]
   print(PosTokens)
   return PosTokens

def main():
  
#  text = "Hello my name is Bob. I am 12 years old."
   text = "How old is Bob."
   PosTokens = Tagging(text)
   #chunking
   chunks = ne_chunk_sents(PosTokens)
   for each in chunks:
      print(each)
      

   #random for loop
   for x in PosTokens:
      for q in x:
         if q[1] == 'NNP':
            print(q[0])

   
   return;

if __name__ == "__main__":
   main()
