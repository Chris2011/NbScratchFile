#Scratch Files for Netbeans
###With this plugin you can create scratch files which will be created in memory. You don't need a project for this. After typing Ctrl + Alt + Shift + N you can select File -> Save as... to save the file to any location wherever you want.

The idea came me while using Sublime Text 2, where you can type Ctrl + N to creates an empty file. IntelliJ has such behaviour too: <a href="http://blog.jetbrains.com/idea/2014/09/intellij-idea-14-eap-138-2210-brings-scratch-files-and-better-mercurial-integration/">IntelliJ IDEA 14 eap 138.2210 brings scratch files...</a>. 
It is not full functional but you can create new in memory files with Ctrl + Alt + Shift + N and Save as... wherever you want.

I found this little example, which I implemented: <a href="http://blogs.kiyut.com/tonny/2007/09/01/netbeans-platform-and-memory-file-system/">Netbeans platform and memory file system</a>

~~Next part is to implement the response from <a href="http://www.kiyut.com/">Tonny Kohar</a> to save this file the first time with a location popup, after~~
Unfortunately it is not possible to have 2 different functionalities for the 
normal save logic. So that means, that you have to add a shortcut to Save As 
for me Ctrl + Alt + S; Ctrl + Alt + S and then you can save the file whereever 
you want and change the name and extension.