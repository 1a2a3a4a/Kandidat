
import socket
#from questioncreater import questionizer
def main():
    sendText = "The chair has four legs. The mouse has two ears."

    # create a socket object
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
    
    # get local machine name
    host = socket.gethostbyname(socket.gethostname())                           
    print(host)
    port = 4999
    
    # connection to hostname on the port.
    s.connect((host, port))
   
    s.send(sendText.encode())

    t = s.recv(1024)
    print(t.decode())

    s.close()
    


if __name__ == "__main__":
    main()
