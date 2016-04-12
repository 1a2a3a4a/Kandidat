from nltk import *
from nltk.corpus import *
from numpy import *



##CC	coordinating conjunction
##CD	cardinal digit
##DT	determiner 
##EX	existential there (like: "there is" ... think of it like "there exists")
##FW	foreign word
##IN	preposition/subordinating conjunction
##JJ	adjective	'big'
##JJR	adjective, comparative	'bigger'
##JJS	adjective, superlative	'biggest'
##LS	list marker	1)
##MD	modal	could, will
##NN	noun, singular 'desk'
##NNS	noun plural	'desks'
##NNP	proper noun, singular	'Harrison'
##NNPS	proper noun, plural	'Americans'
##PDT	predeterminer	'all the kids'
##POS	possessive ending	parent's
##PRP	personal pronoun	I, he, she
##PRP$	possessive pronoun	my, his, hers
##RB	adverb	very, silently,
##RBR	adverb, comparative	better
##RBS	adverb, superlative	best
##RP	particle	give up
##TO	to	go 'to' the store.
##UH	interjection	errrrrrrrm
##VB	verb, base form	take
##VBD	verb, past tense	took
##VBG	verb, gerund/present participle	taking
##VBN	verb, past participle	taken
##VBP	verb, sing. present, non-3d	take
##VBZ	verb, 3rd person sing. present	takes
##WDT	wh-determiner	which
##WP	wh-pronoun	who, what
##WP$	possessive wh-pronoun	whose
##WRB	wh-abverb	where, when

import nltk
from nltk.corpus import state_union
from nltk.tokenize import PunktSentenceTokenizer

#train_text = state_union.raw("2005-GWBush.txt")
#sample_text = state_union.raw("2006-GWBush.txt")



def process_content(tokenized):
    try:
        for i in tokenized:
            words = nltk.word_tokenize(i)
            tagged = nltk.pos_tag(words)
            #chunkGram = r"""Chunk: {<RB.?>*<VB.?>*<NNP>+<NN>?}"""
            chunkGram = r"""Chunk: {<CD>*<NN.?>*<NNPS>*}"""
            chunkParser = nltk.RegexpParser(chunkGram)
            chunked = chunkParser.parse(tagged)
            
            return chunked

    except Exception as e:
        print(str(e))

def process_names(chunked):
   new_list = []
   bind_words ='FW IN '
   i = 0
   for w in chunked:
      if i >= len(chunked):
         break
      #print(w)
      if i+3 < len(chunked):
         if (chunked[i])[1] == 'NNP' and (chunked[i+1])[1] in bind_words and (chunked[i+2])[1] == 'NNP':
            new_list.append(((chunked[i])[0] + ' ' + (chunked[i+1])[0] + ' ' +(chunked[i+2])[0], 'NAME'))
            i += 3
         else:
            new_list.append(chunked[i])
            i += 1
      else:
         new_list.append(chunked[i])
         i += 1
   return new_list

def process_dates(chunked):
   new_list = []
   i = 0
   for w in chunked:
      if(chunked[i][1] == 'CD'):
         str = chunked[i][0]
         if(str[len(str) -2] == 't'):
            new_list.append(((str[:len(str)-2]), 'CD'))
            new_list.append(('th', 'THE'))
         else:
            new_list.append(chunked[i])
      else:
         new_list.append(chunked[i])
      i += 1
   return new_list

def preprocess(chunked):
   new_list = [];
   
   i = 0
   new_list =  process_names(chunked)
   new_list = process_dates(new_list)
   return new_list


def questionize(chunked):
   tags = 'CD NN NNS NNP NNPS NAME'
   chunked = preprocess(chunked)
   black_list = '- – '
   blank_targets = 0
   for w in chunked:
      if w[1] in tags and w[0] not in black_list:
         blank_targets += 1

   print(blank_targets)
   amount = 0
   if blank_targets > 2:
      amount = 2
   if amount < 1:
      amount = 1

   blanked_out = 0
   while(blanked_out < amount):
      question = ''
      y = 0
      for w in chunked:
         print(w)
         if w[1] not in tags or w[0] in black_list:
            question += w[0]
         else:
            if blanked_out < amount:
               roll = random.randint(0,100)
               if roll < 50:
                  question += '\BLANK'
                  blanked_out += 1
               else:
                  question += w[0]
            else:
               question += w[0]
         if(y < len(chunked)-1):
            if(not(chunked[y+1][1] == 'THE' or chunked[y+1][1] == ','  or chunked[y+1][1] == '.')):
               question += ' '
         y += 1
   return question

if __name__ == "__main__":
   while True:
      train_text = input("Input text: ")
      if input == 'stop':
         break
      custom_sent_tokenizer = PunktSentenceTokenizer(train_text)
      tokenized = custom_sent_tokenizer.tokenize(train_text)
      for i in tokenized:
         words = word_tokenize(i)
         tagged = pos_tag(words)
      #chunked = process_content(tokenized)
      #print(chunked)
      print(tagged)
      for sentence in tagged:
         
         print(sentence)
         #print(questionize(sentence))

