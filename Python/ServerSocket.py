import socket
import time
from test import Tagging
from Daniel_tests import gen_questions
#tonys dator: 130.238.92.16
#port 4999
def main():

# create a socket object
    serversocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 

    # get local machine name
    host = socket.gethostbyname(socket.gethostname())
    print(host)
    port = 4999

    # bind to the port
    serversocket.bind((host, port))                                  

    # queue up to 5 requests
    serversocket.listen(5)                                           

    while True:
        # establish a connection
        clientsocket,addr = serversocket.accept()      
        
        print("Got a connection from %s" % str(addr))
        receiveText =  clientsocket.recv(1024).decode()
        Question  = gen_questions(receiveText)
        print(Question)
        clientsocket.send(Question.encode())
        
        clientsocket.close()
        

        
if __name__ == "__main__":
            main()
