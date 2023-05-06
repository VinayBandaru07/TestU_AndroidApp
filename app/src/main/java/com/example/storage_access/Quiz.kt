package com.example.storage_access


import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import androidx.appcompat.app.AppCompatActivity


import java.io.File
import java.io.FileInputStream
import kotlin.random.Random
import android.R.color
import com.example.storage_access.databinding.ActivityQuizBinding
import java.util.*
import kotlin.collections.ArrayList


class Quiz : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getSupportActionBar()?.hide()
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        var FilePosition = intent.getIntExtra("SelectedPosition", 1)
        Log.d("FilePosition", FilePosition.toString())

        var Target_file_path: String = "path"

        var presentIndex: Int = 1

        var Score: Int = 0


        var colorCorrectAnswer: Int = getResources().getColor(R.color.green)
        var colorOriginal: Int = getResources().getColor(R.color.black)
        var colorRed: Int = getResources().getColor(R.color.red)
//        Environment.getExternalStorageDirectory().toString() + "/Pictures"


//        fun searchStorage() {
////            val path = Environment.getExternalStorageDirectory().toString() + "/Pictures"
//            val path = Environment.getExternalStorageDirectory().toString()+"/WhatsApp/Media/WhatsApp Documents"
//            Log.d("Files", "Path: $path")
//            val f = File(path)
//            var i: Int = 0
//            var count: Int = 0
//            val file: Array<File> = f.listFiles()
//
//            while (count <= FilePosition) {
//                if (file[i].getName().substringAfterLast(".", "") == "csv") {
////                    Target_file_path = Environment.getExternalStorageDirectory()
////                        .toString() + "/Pictures/" + file[i].getName()
//                    Target_file_path = Environment.getExternalStorageDirectory().toString()+"/WhatsApp/Media/WhatsApp Documents/" + file[i].getName()
//                    count += 1
//                }
//                i += 1
//            }
//
//        }
//        searchStorage()


        var name = ""
        var questionWord = ""
        var option_1 = mutableListOf("1")
        var option_2 = mutableListOf("1")
        var option_3 = mutableListOf("1")
        var option_4 = mutableListOf("1")
        var answerList = mutableListOf("1")
        var questionList = mutableListOf("1")
//        var lines: List<String> = File(Target_file_path).readLines(
        var lines : List<String> =listOf<String>("QUESTION*OPTION-1*OPTION-2*OPTION-3*OPTION-4*ANSWER",
            "1 Which of the following is not an operating system?*Windows*Linux *Oracle *DOS *3",
            "2 What is booting mean in Operating System?*Restarting computer*Install of mentioned a program*Scan*Turn on*1",
            "3 Which of the following operating systems does not support more than one program at a time?*Linux*Windows*DOS*MAC *3",
            "4 Which is a system software? *OS *Compiler*OS&Compiler* None of the mentioned *3",
            "5 The following are correct about Windows OS?* None of the mentioned *Windows is CUI OS*Windows is GUI OS *It is CUI based*3",
            "6 Which OS runs on server? *Batch OS *Network OS*Real-Time OS* None of the mentioned*2",
            "7 Which OS does not need command to run?* None of the mentioned*Linux *Windows*Unix*3",
            "8 The OS works between*User*User and Computer*All of mentioned *One to another user*2",
            "9 To access OS interface is provided by*System cAll of mentioned *library *API * None of the mentioned *1",
            "10 Open source of OS is*Windows *Unix & Linux* None of the mentioned *Windows & Unix*2",
            "11 Lack of Interaction between user and job is seen in which OS?*Real-time OS *Network OS *Batch OS* None of the mentioned *3",
            "12 Example of Network OS are?*Unix and Linux*MAC OS X *BSD*All of mentioned of the mentioned*4",
            "13 Types of Real-Time OS are? *Hard Real-time Systems *Soft real-time Systems * None of the mentioned*Hard Real-time Systems &Soft real-time Systems*4",
            "14 OS provides services like? *Program execution *Communication *Error Detection*All of mentioned of the mentioned*4",
            "15 A file is *collection of related information*Information *Text * None of the mentioned *1",
            "16 OS manages All of mentioned kinds of resources using *Utilities*Schedulers *All of mentioned location* None of the mentioned*2",
            "17 Multitasking Operating Systems are also known as*Real-time OS*Soft real-time systems *Time Sharing Systems * None of the mentioned *3",
            "18 A process is known as *process in execution *only execution * None of the mentioned *program in execution *4",
            "19 Process Control Block is a*Data *Data Structure*Information * None of the mentioned *2",
            "20 Components of OS are*Process Management *File Management*Network Management *All of mentioned of the mentioned*4",
            "21 The main operations in OS are *File Management*Memory Management *Devices*File Management &Memory Management *4",
            "22 Files in the system accessed in *3 ways *4 ways *2 ways * None of the mentioned*3",
            "23 Some ways of Debugging is * None of the mentioned * None of the mentioned *Core files *Dumping *1",
            "24 Message logs stores*only public messages*only private messages * public and private messages * None of the mentioned*3",
            "25 Debugging means *process of finding problems & solving *only solving problems *process of finding problems & solving and only solving problems * None of the mentioned*1",
            "26 Which are not functions of OS? *Devices*Security*Memory Management *File Management *1",
            "27 Which is responsible for handling All of mentioned kinds of inputs? *Keyboard *Mouse *OS * None of the mentioned*3",
            "28 Which are not mobile Operating Systems? *Unix *Bada *Blackberry *Android OS*1",
            "29 The OS keeps programs and system safe through *ID *Authentication*Only password* None of the mentioned*2",
            "30 RTOS is*Real time of OS *Rare Transaction of OS *Real Time OS* None of the mentioned*3",
            "31 The problem with Single User Single Task Operating Systems is *No speed*Arranged in stack only * None of the mentioned*Arranged in queue *4",
            "32 How many generations are there of Operating System?*many*4*6*can’t decide *2",
            "33 The first generation started from year *1940*1950*1997*1999*1",
            "34 GMOS stands for*General management OS*Generic Motor OS*General Motors OS * None of the mentioned *3",
            "35 The fourth generation of OS started from *1980-present *1960-present *1950-present*1997-present*1",
            "36 The first electronic computer was discovered in *1980*1940*1990*1970*2",
            "37 The first OS is cAll of mentioneded as *GM*GOS*GMOS*GSOM*3",
            "38 The third generation started from 1965 to *1960*1970*1990*1980*4",
            "39 MS-DOS was introduced in the year *1980*1981*1987*1990*2",
            "40 How many system cAll of mentioneds are there in OS?*5*7*8*3*1",
            "41 Which system cAll of mentioneds are used for interprocess communication?*Device Management*File Management*Communication* None of the mentioned*3",
            "42 The exit() system cAll of mentioned is used by a program to*continue execution*terminate program execution*exiting* None of the mentioned*2",
            "43 Which system cAll of mentioned is used by OS to send termination signal to process that urges the process to exit?* None of the mentioned*exit()*wait()* kill()*4",
            "44 System cAll of mentioned provides a interface between OS and*process*program*user* None of the mentioned*1",
            "45 File management is one of the type of*Part of OS*Related to manage*system cAll of mentioneds* None of the mentioned*3",
            "46 Which system cAll of mentioned perform process creation process termination etc?* None of the mentioned*Process Control*User control*Process Control & User control*2",
            "47 Important system cAll of mentioned used in OS are?*exec()*kill()*exit()*All of mentioned of the mentioned*4",
            "48 API stands for?*Assitance program Interconnect* None of the mentioned*Application Program Interface*Apply program Intercommunication*3",
            "49 When computer software needs to access OS kernel it makes a*System cAll of mentioned*CAll of mentioneds process*Interact with user* None of the mentioned*1",
            "50 Which is NOT a system cAll of mentioned in OS?*Communication*Information*Information Maintenance*Device Management*2",
            "51 Examples of Communication system cAll of mentioned is?*send messages*receive message*delete communication connections*All of mentioned of the mentioned *4",
            "52 read() system cAll of mentioned All of mentioned you to obtain data from a *process *program *file*data created *3",
            "53 When a program is loaded into memory that it becomes a*process *Executed * None of the mentioned*progressive *1",
            "54 A process can be divided into _______ sections?*6*4*8*2*2",
            "55 Heap is a dynamicAll of mentioned All of mentioned located memory to a* None of the mentioned*execute data*process in compile time *process during its run time *4",
            "56 A part of a computer program that performs a well-defined task is cAll of mentioneded as__________? *program*Data*algorithm*logic *3",
            "57 OS maintains a _______________ data structure for every process?*Process state*process status* None of the mentioned*Process Control Block*4",
            "58 Process state means?*current state of process * None of the mentioned *current process rank *process state value *1",
            "59 The Architecture of PCB is completely dependent on? *Process*OS *CPU* None of the mentioned *2",
            "60 Which is NOT a section of process memory?*Heap*Stack*Data stored* None of the mentioned*3",
            "61 A process has a dynamic instance of__________________?*Data *Information * None of the mentioned *code and data *4",
            "62 The minimum number of states in process state is?*5*3*7*9*1",
            "63 A program which is going to picked up by the OS into the main memory is cAll of mentioned?*ready process*new process *running process * None of the mentioned*2",
            "64 When a process finishes its execution it comes in the?*executed state * None of the mentioned*termination state *scheduling*3",
            "65 Operations on the process are?*Creation *Scheduling*Execution *All of mentioned *4",
            "66 Which are the types of schedulers? *Long term *Long term & Short term*Short term* None of the mentioned *2",
            "67 Pre-emption and Non pre-emption are the types of? *Multiprogramming *programming * None of the mentioned*Multiprogramming  & programming *1",
            "68 The no of processes that can reside in the ready state at maximum decides the* None of the mentioned *programming *degree of multiprogramming*program execution*3",
            "69 A process is the *code*data* None of the mentioned *running instance of code *4",
            "70 Which of the following are the properties of good scheduling algorithm?*CPU utilization should be 100%  & )response time should be minimum for users*CPU utilization should be 100%*response time should be minimum for users * None of the mentioned *1",
            "71 In OS which of the following is the scheduling algorithms? *Shortest job first *Shortest job first & Priority  *Priority * None of the mentioned*2",
            "72 CPU scheduling is a basis of? * None of the mentioned *multiprocessor systems *multiprogramming Operating Systems *OS *3",
            "73 Hybrid kernel can be seen in? *Windows* None of the mentioned *can’t say *OS X *4",
            "74 Main memory accommodates*Operating System*Can’t say*Schedulin*All of mentioned *1",
            "75 In Unix which system cAll of mentioned creates the new process? *new *fork * None of the mentioned*create*2",
            "76 This command is used to delete files on a linux system?*Rm * None of the mentioned*delete *create*1",
            "77 The ‘text editor’ for Microsoft Windows is?* None of the mentioned *Notepad *Word pad *Notepad  & Word pad *2",
            "78 A common boundary between two computer systems is known as? *Surface*Interconnection*Interface* None of the mentioned*3",
            "79 OS does not boot itself when a system is *Shutdown*Restarted *powered on * None of the mentioned *1",
            "80 Which of the following is group of programs?*Accessories*Paint*Word*All of mentioned*1",
            "81 A process is more than the code which is some times called as*Text Section*stack*Data Section*None  of the mentioned*4",
            "82 PCB stands for?*Process Current Block*Parent Control Block*Parent Current Block*Process Control Block*4",
            "83 In which state processor executes its instructions?*Ready*Waiting*Running*Start*3",
            "84 A thread is also cAll of mentioned ?*heavyweight process* lightweight process*data segment process*overhead process*2",
            "85 Which of the following is not an advantage about thread?*Threads minimize the context switching time*Use of threads provides concurrency within a process.*kernel is single threaded*All of mentioned *3",
            "86 Which of the following do not belong to queues for processes?*Job Queue*PCB queue*Device Queue*Ready Queue*2",
            "87 What will happen when a process terminates?*It is removed from All of mentioned queues*It is removed from All of mentioned  but the job queue*Its process control block is de-All of mentionedocated*Its process control block is never de-All of mentionedocated*1",
            "88 Suppose that a process is in “Blocked” state waiting for some I/O service. When the service is completed it goes to the __________*Running state*Ready state*Suspended state*Terminated state*2",
            "89 The child process can __________*be a duplicate of the parent process*never be a duplicate of the parent process*cannot have another program loaded into it*never have another program loaded into it*1",
            "90 The child process completes execution but the parent keeps executing then the child process is known as __________*Orphan*Zombie*Body*Dead*2",
            "91 What is meant by Interprocess communication?*All of mentioned lows processes to communicate and synchronize their actions when using the same address space*All of mentionedows processes to communicate and synchronize their actions*All of mentionedows the processes to only synchronize their actions without communication* None of the mentioned *2",
            "92 The Scheduling Algorithms are ________.*non-preemptive*preemptive* non-preemptive & preemptive* None of the mentioned*3",
            "93 Which of the following is process scheduling algorithms?*FCFS*SJF*RR*All of mentioned *4",
            "94 A Process Control Block(PCB) does not contain which of the following?*Code*Stack*Bootstrap program*Data*3",
            "95 Where are placed the list of processes that are prepared to be executed and waiting?*Job queue*Ready queue*Execution queue*Process queue*2",
            "96 Which of the following does not interrupt the running process?*Timer interrupt*Device*Power failure*Scheduler process*4",
            "97 The state of a process is defined by __________*the final activity of the process*the activity just executed by the process*the activity to next be executed by the process* the current activity of the process*4",
            "98 Which of the following is not the state of a process?*New*Old*Waiting*Running*2",
            "99 What is a Process Control Block?*Process type variable*Data Structure*A secondary storage section*A Block in memory*2",
            "100 A single thread of control All of mentioned lows the process to perform __________*only one task at a time*multiple tasks at a time*only two tasks at a time*All of mentioned*1",
            "101 With _____________ only one process can execute at a time; meanwhile All of mentioned other process are waiting for the processor. *Multiprocessing and Multiprogramming*Multiprogramming and Uniprocessing*Multiprogramming and uniprogramming*Uniprogramming and Multiprocessing*4",
            "102 Restricting the child process to a subset of the parent’s resources prevents any process from __________*overloading the system by using a lot of secondary storage*under-loading the system by very less CPU utilization* overloading the system by creating a lot of sub-processes*crashing the system by utilizing multiple resources*3",
            "103 In Operating Systems which of the following  are CPU scheduling algorithms?*Round Robin*Shortest Job First*Priority*All of mentioned *4",
            "104 If a process fails most operating system write the error information to a ______*log file*another running process*new file* None of the mentioned*1",
            "105 The interval from the time of submission of a process to the time of completion is termed as ____________*waiting time*turnaround time* response time*throughput*2",
            "106 Which scheduling algorithm All of mentionedocates the CPU first to the process that requests the CPU first?*first-come  first-served scheduling*shortest job scheduling*priority scheduling*  None of the mentioned*1",
            "107 The real difficulty with SJF in short term scheduling is ____________*it is too good an algorithm*knowing the length of the next CPU request*it is too complex to understand* None of the mentioned*2",
            "108  Preemptive Shortest Job First scheduling is sometimes cAll of mentioneded ____________*Fast SJF scheduling*EDF scheduling – Earliest Deadline First*HRRN scheduling – Highest Response Ratio Next*SRTN scheduling – Shortest Remaining Time Next*4",
            "109 Scheduling is done so as to ____________*increase CPU utilization*decrease CPU utilization* keep the CPU more idle* None of the mentioned*1",
            "110 What is Waiting time?* the total time in the blocked and waiting queues*the total time spent in the ready queue*the total time spent in the running queue*the total time from the completion till the submission of a process*2",
            "111 What are the two steps of a process execution?*I/O & OS Burst*CPU & I/O Burst*Memory & I/O Burst*OS & Memory Burst*2",
            "112 A process is selected from the ______ queue by the ________ scheduler to be executed.* blocked and short term* wait and long term*ready and short term*ready and long term*3",
            "113 A process can be terminated due to __________* normal exit*fatal error*killed by another process*All of mentioned *4",
            "114 What is interprocess communication?* communication within the process*communication between two process*communication between two threads of same process* None of the mentioned*2",
            "115 The number of processes completed per unit time is known as __________*Output*Throughput*Efficiency*Capacity*2",
            "116 The state of a process is defined by __________*the final activity of the process*the activity just executed by the process*the activity to next be executed by the process*the current activity of the process*4",
            "117  Which of the following is not the state of a process?*New*Old*Waiting*Running*2",
            "118 The entry of All of mentioned the PCBs of the current processes is in __________*Process Register*Program Counter*Process Table*Process Unit*3",
            "119 The FCFS algorithm is particularly troublesome for ____________*time sharing systems*multiprogramming systems*multiprocessor systems*operating systems*2",
            "120 An SJF algorithm is simply a priority algorithm where the priority is ____________* the predicted next CPU burst* the inverse of the predicted next CPU burst*the current CPU burst*anything the user wants*1",
            "121 Choose one of the disadvantages of the priority scheduling algorithm?*it schedules in a very complex manner* its scheduling takes up a lot of time*it can lead to some low priority process waiting indefinitely for the CPU* None of the mentioned*3",
            "122 Which of the following statements are true? \n i) Shortest remaining time first scheduling may cause starvation\n ii) Preemptive scheduling may cause starvation iii) Round robin is better than FCFS in terms of response time*i only*i and iii only*ii and iii only* i and ii and iii*4",
            "123  Which of the following scheduling algorithms gives minimum average waiting time?*FCFS*SJF*Round – robin*Priority*2",
            "124 Which is the most optimal scheduling algorithm?*FCFS – First come First served* SJF – Shortest Job First*RR – Round Robin* None of the mentioned*2",
            "125 Who among the following can block the running process?*Fork*Read*Down*All of mentioned*4",
            "126 Which of the following scheduling algorithms is preemptive scheduling?*FCFS Scheduling*SJF Scheduling*Network Scheduling*SRTF Scheduling*4",
            "127 Which of the following scheduling reduces process flow time?*FCFS*LIFO*SJF*All of mentioned *2",
            "128 Which of the following component does not belong to PCB (Process Control Block)?*CPU registers* CPU scheduling information*Operating System information*Accounting information*3",
            "129 SSTF stands for ________.*Shortest Signal Time First*Shortest Seek Time First*System Seek Time First*System Shortest Time First*2",
            "130 The PCB is identified by ___________.*Real-Number*Binary Number*Store block* Integer Process ID*4",
            "131 What type of scheduling is round-robin scheduling?*Linear data scheduling*Non-linear data scheduling*Preemptive scheduling*Non-preemptive scheduling*3",
            "132 Which of the following scheduling algorithm is non-preemptive scheduling?*SJF scheduling*Round-Robin scheduling*SRTF scheduling* None of the mentioned*1",
            "133 What is the objective of multiprogramming?* Have a process running at All of mentioned time*Have multiple programs waiting in a queue ready to run* To increase CPU utilization* None of the mentioned*3",
            "134 What is an operating system?*collection of programs that manages hardware resources*system service provider to the application programs*interface between the hardware and application programs*All of mentioned *4",
            "135  CPU scheduling is the basis of ___________*multiprocessor systems*multiprogramming operating systems*larger memory sized systems* None of the mentioned *2",
            "136  In the following cases non – preemptive scheduling occurs?*When a process switches from the running state to the ready state*When a process goes from the running state to the waiting state* When a process switches from the waiting state to the ready state* All of mentioned *2",
            "137  What is Turnaround time?*the total waiting time for a process to finish execution*the total time spent in the ready queue*the total time spent in the running queue* the total time from the completion till the submission of a process*4",
            "138 In priority scheduling algorithm ____________*CPU is All of mentioned located to the process with highest priority*CPU is All of mentioned located to the process with lowest priority* Equal priority processes can not be scheduled* None of the mentioned*1",
            "139  Which algorithm is defined in Time quantum?* shortest job scheduling algorithm*round robin scheduling algorithm*priority scheduling algorithm*multilevel queue scheduling algorithm*2",
            "140 What is Response time?*the total time taken from the submission time till the completion time*the total time taken from the submission time till the first response is produced*the total time taken from submission time till the response is output* None of the mentioned *2",
            "141 Shortest Job Next is also known as?* Batch job*Advance job*shortest job first*shortest job last*3",
            "142 In Priority Based Scheduling if Processes have same priority then which Scheduling algorithm is used?*SJN*FCFS*SRT*Round Robin*2",
            "143 What is true about thread?*Thread switching does not need to interact with operating system.*All of mentioned threads can share same set of open files child processes.*Multiple threaded processes use fewer resources.*All of mentioned*4",
            "144 In how many ways Threads are implemented?*Two*Three*Four*Five*1",
            "145 Multithreading models are _________ types?*2*3*4*5*2",
            "146 Multithreading on a multi : CPU machine ___________*decreases concurrency*increases concurrency*doesn't affect the concurrency* can increase or decrease the concurrency*2",
            "147 Which of the following is true about Process?*A process is basicAll of mentioned a program in execution*The execution of a process must progress in a sequential fashion.*A process is defined as an entity which represents the basic unit of work to be implemented in the system* All of mentioned *4",
            "148 How many state are there in Process Life Cycle?*4*5*6*7*2",
            "149 In PCB This is required to All of mentioned low/disAll of mentioned low access to system resources.*Process State*Process privileges* Program Counter*CPU Scheduling Information*2",
            "150 The OS maintains All of mentioned PCBs in?*Process Scheduling Queues*Job queue*Ready queue*Device queues*1",
            "151 Non contiguous memory All of mentioned location is a model in which*Portion of its address space are distributed among many area of memory*All of mentioned the process is All of mentionedocated a single continuous area in the memory* Each process is All of mentionedocated a single continuous area in the memory*All of mentioned*1",
            "152 In NonContiguous memory All of mentionedocation*It increases the external fragmentation*It reduce the external fragmentation*It increases the internal fragmentation*It reduce the internal fragmentation *2",
            "153 Logical address is the address* Constitutes the physical address space*Of an instruction or data byte as used in a process*In a memory where an instruction or data byte exists*All of mentioned*2",
            "154 Physical address is the address _____*Constitutes the logical address space*Of an instruction or data byte as used in a process*In a memory where an instruction or data byte exists*All of mentioned*3",
            "155 Which of the following is the fundamental approaches to implement Non-contiguous memory*Paging ans Segmentation*Segmentation and Memory campaction*Memory compaction and power of 2 All of mentionedocator*Paging and power of 2 All of mentionedocator*1",
            "156 Physical memory is broken into fixed-sized blocks cAll of mentioned ________*Frames*Pages*Backing store* None of the mentioned*1",
            "157  Logical memory is broken into blocks of the same size cAll of mentioned _________*Frames*Backing Store*Pages* None of the mentioned*3",
            "158 Every address generated by the CPU is divided into two parts. They are ____________*Frame bit & Page number*Page number & Page offset*Page offset & Frame bit*Frame offset & Page offset*2",
            "159 The __________ is used as an index into the page table.*Frame bit & Page number*Page offset*Page number*Frame offset*3",
            "160 The _____ table contains the base address of each page in physical memory.*Process*Memory*Page*Frame*3",
            "161 Each process consists of fixed size component cAll of mentioned ___*Pages*Page table*Segment*All of mentioned*1",
            "162 In which of the memory All of mentioned location techniques internal fragmentation can rise*Segmentation*Paging*Segmentation with paging *All of mentioned*2",
            "163 A data structure used by the virtual memory system to store the mapping between logical addresses and physical addresses is cAll of mentioned*Structure of the page table*Pages*Frames* None of the mentioned*1",
            "164 Information about an individual page in a process's logical address space is stored in*Frames*Page table*Pages*Segment table*2",
            "165 Another name for Hierarchical paging is *Multiple paging*singlelevel paging*Multilevel paging* None of the mentioned*3",
            "166 The chunks that a program is divided into which are not necessarily All of mentioned of the same sizes are cAll of mentioned*Pages*Frames*Blocks*Segments*4",
            "167 If there are 32 segments each of size 1Kb then the logical address should have :*16 bits*14 bits*15 bits*13 bits*4",
            "168 When the entries in the segment tables of two different processes point to the same physical location :*The segments are invalid*The processes get blocked*Segments are shared*All of mentioned*3",
            "169 If the offset is legal :*It is used as a physical memory address itself*It is subtracted from the segment base to produce the physical memory address*It is added to the segment base to produce thephysical memory address* None of the mentioned*1",
            "170 The segment base contains the:*Starting logical address of the process*Starting physical address of the segment in memory*Segment length* None of the mentioned*2",
            "171 In paging the user provides only ________ which is partitioned by the hardware into ________ and _______*No address page number offset*One offset page number address*Page number offset address*One address page number offset*4",
            "172 Each entry in a segment table has a:*Segment value*Segment peak*Segment number*Segment base*4",
            "173 With paging there is no ________ fragmentation.*Internal*External*Either type of*All of mentioned*2",
            "174 Partitions of the secondary memory are commonly known as *Segments*Frames*Pages*Partitions*1",
            "175 A Table that is used to store the information of All of mentioned segments of the process is commonly known as*Page table*Frame Table*Segment table* None of the mentioned*3",
            "176 The table that stores the base address of the segment table is commonly known as*Segment table base register*Segment table length register*Both* None of the mentioned*1",
            "177 In segmentation technique there is no _________ fragmentation*External*Internal*Both *All of mentioned*2",
            "178 The size of a page is typicAll of mentioned by ____________*Varied*Power of  6*Power of 4*Power of 2*4",
            "179  Paging increases the ______ time.*Waiting*Execution*Context-switch*All of mentioned*3",
            "180 SmAll of mentioned per page tables are implemented as a set of _______*Queues*Stacks*Counters*Registers*4",
            "181 A mechanism in which a process can be swapped temporarily out of main memory to secondary storage and make that memory available to other processes.*paging*   fragmentation*    Swapping*mapping*3",
            "182   In which memory the swapped-out process is stored*     Primary*Secondary*Main*All of mentioned*2",
            "183 The purpose of the swapping in operating system is to*Swap the data*Manipulate the data* Access the data*  None of the mentioned*3",
            "184 The place where the swapped-out process is stored is known as* Bin space*Swap space*Store space*Space*3",
            "185  The process of swapping affects the performance of the system it helps to run larger and more than one process which is also referred as*Memory compaction*Memory extension*Memory management* None of the mentioned*1",
            "186 What is the swap space in the disk used for?*Saving temporary html pages*Saving process data*Storing the super-block* Storing device drivers*2",
            "187 The ___________ swaps processes in and out of the memory.*     Memory manager*CPU*CPU Manager*User*1",
            "188 The ____________ time in a swap out of a running process and swap in of a new process into the memory is very high.*Context-switch*Waiting* Execution* All of mentioned*1",
            "189 Swapping requires a________* Motherboard*Keyboard*Monitor*Backing store*4",
            "190  Swap space is All of mentioned located ______*As a chunk of disk*Separate from a file system* Into a file system*All of mentioned*1",
            "191 What is the maximum length of the filename in DOS?*4*5*8*12*3",
            "192 When were MS windows operating systems proposed?*1994*1990*1992*1985*4",
            "193 What else is a command interpreter cAll of mentioneded?*prompt*kernel*shell*command*3",
            "194 BIOS is used?*By operating system*By compiler*By interpreter*By application software*1",
            "195 What is the mean of the Booting in the operating system?* Restarting computer*InstAll of mentioned the program*To scan*To turn off*1",
            "196 Which of the following is a single-user operating system?*Windows*MAC*Ms-Dos",
            "* None of the mentioned of these*3",
            "197 Which of the following is not application software?*Windows 7*WordPad*Photoshop*MS-excel*1",
            "198 Which of the following supports Windows 64 bit?*Window XP*Window 2000*Window 1998* None of the mentioned of these*1",
            "199 Who provides the interface to access the services of the operating system?*API*System cAll of mentioned*Library*Assembly instruction*2",
            "200 What is Microsoft window?*Operating system*Graphics program*Word Processing*Database program*1"
        )
        var column = 0


        fun SeperateCSV() {


            for (i in 0..lines.size - 1) {
                name = lines[i]
                for (j in 0..name.length - 1) {

                    if (column == 0) {
                        if ((name[j].toString() == "*")) {
                            questionList.add(questionWord)
                            questionWord = ""
                            column = column + 1
                        } else if ((j == name.length - 1)) {
                            questionWord = questionWord + name[j].toString()
                            questionList.add(questionWord)
                            questionWord = ""


                        } else {
                            questionWord = questionWord + name[j].toString()
                        }
                    } else if (column == 1) {

                        if ((name[j].toString() == "*")) {
                            option_1.add(questionWord)
                            questionWord = ""
                            column = column + 1
                        } else if ((j == name.length - 1)) {
                            questionWord = questionWord + name[j].toString()
                            option_1.add(questionWord)
                            questionWord = ""

                        } else {
                            questionWord = questionWord + name[j].toString()
                        }

                    } else if (column == 2) {


                        if ((name[j].toString() == "*")) {
                            option_2.add(questionWord)
                            questionWord = ""
                            column = column + 1
                        } else if ((j == name.length - 1)) {
                            questionWord = questionWord + name[j].toString()
                            option_2.add(questionWord)
                            questionWord = ""

                        } else {
                            questionWord = questionWord + name[j].toString()
                        }

                    } else if (column == 3) {

                        if ((name[j].toString() == "*")) {
                            option_3.add(questionWord)
                            questionWord = ""
                            column = column + 1
                        } else if ((j == name.length - 1)) {
                            questionWord = questionWord + name[j].toString()
                            option_3.add(questionWord)
                            questionWord = ""


                        } else {
                            questionWord = questionWord + name[j].toString()
                        }
                    } else if (column == 4) {
                        if ((name[j].toString() == "*")) {
                            option_4.add(questionWord)
                            questionWord = ""
                            column = column + 1
                        } else if ((j == name.length - 1)) {
                            questionWord = questionWord + name[j].toString()
                            option_4.add(questionWord)
                            questionWord = ""

                        } else {
                            questionWord = questionWord + name[j].toString()
                        }
                    } else {
                        if ((name[j].toString() == "*")) {
                            answerList.add(questionWord)
                            questionWord = ""
                            column = 0

                        } else if ((j == name.length - 1)) {
                            questionWord = questionWord + name[j].toString()
                            answerList.add(questionWord)
                            questionWord = ""
                            column = 0

                        } else {
                            questionWord = questionWord + name[j].toString()
                        }
                    }
                }
            }
            for (i in 0..questionList.size - 1) {
                Log.d("matter", questionList[i])
            }

            for (i in 0..option_1.size - 1) {
                Log.d("matter", option_1[i])
            }

            for (i in 0..option_2.size - 1) {
                Log.d("matter", option_2[i])
            }
            for (i in 0..option_3.size - 1) {
                Log.d("matter", option_3[i])
            }
            for (i in 0..option_4.size - 1) {
                Log.d("matter", option_4[i])
            }
            for (i in 0..answerList.size - 1) {
                Log.d("matter", answerList[i])
            }
        }
        SeperateCSV()

        var quizQuestions = mutableListOf("1")
        var quizOption_1 = mutableListOf("1")
        var quizOption_2 = mutableListOf("1")
        var quizOption_3 = mutableListOf("1")
        var quizOption_4 = mutableListOf("1")
        var quizAnswers = mutableListOf("1")


        fun randomGen() : Int{
            return (1..19).random()
        }

//        var value = 0
//        var randomValues = mutableListOf<Int>()
//        for(i in 0..9){
//            value = randomGen()
//            if(randomValues.contains(value)){
//                value=randomGen()
//                if(randomValues.contains(value)){
//                    value=randomGen()
//                }
//            }
//            randomValues.add(value)
//
//        }
//        val randomValues = List(10) { Random.nextInt(1, questionList.size) }
//        Log.d("values", randomValues.toString())

//        var randValues : MutableList<Int> = mutableListOf<Int>()
//        for(i in 0..20){
//            randValues.add(i)
//        }

        val randomValues = mutableListOf<Int>()
        for(i in 2..198){
            randomValues.add(i)
        }
        Collections.shuffle(randomValues)




//        Collections.shuffle(randomValues)
//
//        val randomVal: MutableSet<Int> = mutableSetOf()
//        while (randomVal.size < 10) { randomVal.add((1..questionList.size).random()) }
//        val randomValues = randomVal.toList()

//        val list = ArrayList<Int>()
//        val randomValues = ArrayList<Int>()
//        for (i in 1..21) {
//            list.add(i)
//        }
//        Collections.shuffle(list)
//        for (i in 1..10) {
//            randomValues.add(list[i])
//        }


        Log.d("inspect", questionList.toString())
        for (i in 0..19) {
            var value = randomValues[i]
            quizQuestions.add(questionList[value])
            quizOption_1.add(option_1[value])
            quizOption_2.add(option_2[value])
            quizOption_3.add(option_3[value])
            quizOption_4.add(option_4[value])
            quizAnswers.add(answerList[value])

        }





        fun makeColor(Answer: String, color: Int = colorCorrectAnswer) {
            when (Answer) {

                "1" -> binding.option1View.setTextColor(color)
                "2" -> binding.option2View.setTextColor(color)
                "3" -> binding.option3View.setTextColor(color)
                "4" -> binding.option4View.setTextColor(color)
                "A" -> binding.option1View.setTextColor(color)
                "B" -> binding.option2View.setTextColor(color)
                "C" -> binding.option3View.setTextColor(color)
                "D" -> binding.option4View.setTextColor(color)
                "a" -> binding.option1View.setTextColor(color)
                "b" -> binding.option2View.setTextColor(color)
                "c" -> binding.option3View.setTextColor(color)
                "d" -> binding.option4View.setTextColor(color)

            }


        }

        fun UpdateScore(answer: String, clickedOption: Int) {
            if (clickedOption == 1) {
                when (answer) {
                    "1" -> Score += 1
                    "a" -> Score += 1
                    "A" -> Score += 1
                }
            }
            if (clickedOption == 2) {
                when (answer) {
                    "2" -> Score += 1
                    "b" -> Score += 1
                    "B" -> Score += 1
                }
            }
            if (clickedOption == 3) {
                when (answer) {
                    "3" -> Score += 1
                    "c" -> Score += 1
                    "C" -> Score += 1
                }
            }
            if (clickedOption == 4) {
                when (answer) {
                    "4" -> Score += 1
                    "d" -> Score += 1
                    "D" -> Score += 1
                }
            }
        }


        fun makeColorRed(ClickedOption: Int) {
            when (ClickedOption) {
                1 -> binding.option1View.setTextColor(colorRed)
                2 -> binding.option2View.setTextColor(colorRed)
                3 -> binding.option3View.setTextColor(colorRed)
                4 -> binding.option4View.setTextColor(colorRed)

            }
        }

        var switch = 0

        binding.option1View.setOnClickListener() {
            if (switch == 0) {
                makeColorRed(1)
                makeColor(quizAnswers[presentIndex].toString())
                switch = 1
                UpdateScore(quizAnswers[presentIndex].toString(),1)
            }

        }

        binding.option2View.setOnClickListener() {
            if (switch == 0) {
                makeColorRed(2)
                makeColor(quizAnswers[presentIndex].toString())
                switch = 1
                UpdateScore(quizAnswers[presentIndex].toString(),2)
            }

        }

        binding.option3View.setOnClickListener() {
            if (switch == 0) {
                makeColorRed(3)
                makeColor(quizAnswers[presentIndex].toString())
                switch = 1
                UpdateScore(quizAnswers[presentIndex].toString(),3)
            }

        }

        binding.option4View.setOnClickListener() {
            if (switch == 0) {
                makeColorRed(4)
                makeColor(quizAnswers[presentIndex].toString())
                switch = 1
                UpdateScore(quizAnswers[presentIndex].toString(),4)
            }

        }




        fun makeThePaper() {
            binding.questionView.text = quizQuestions[presentIndex]
            binding.option1View.text = quizOption_1[presentIndex]
            binding.option2View.text = quizOption_2[presentIndex]
            binding.option3View.text = quizOption_3[presentIndex]
            binding.option4View.text = quizOption_4[presentIndex]

        }
        makeThePaper()

        fun makeColorsNormal() {
            binding.PresentQuestion.text = "Question-" + (presentIndex).toString()
            binding.option1View.setTextColor(colorOriginal)
            binding.option2View.setTextColor(colorOriginal)
            binding.option3View.setTextColor(colorOriginal)
            binding.option4View.setTextColor(colorOriginal)

        }



        binding.nextPage.setOnClickListener() {
            if ((quizQuestions.size - 1) > presentIndex) {
                presentIndex += 1
                switch = 0
                makeColorsNormal()
                makeThePaper()
            } else {

                val builder = AlertDialog.Builder(this)
                //set title for alert dialog
                builder.setTitle("Result")
                //set message for alert dialog
                builder.setMessage("Your score is: " + Score.toString())
                builder.setPositiveButton("Okay") { dialogInterface, which ->
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()
            }
        }

        val builder = AlertDialog.Builder(this)
        builder.setMessage("1.Idhi Test Mode\n" +
                "2.Indulo 20 random questions select chesi istam for testing yourself\n" +
                "3.Option meeda click cheste answer vastundi\n" +
                "4.Last lo marks sariga rakapothe malli chadivi rayandi😡")
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()

//        binding.SearchButton.setOnClickListener(){
//            if(binding.GOTO.text.toString() !=""){
//                if(binding.GOTO.text.toString().toInt() <= totalQuestions) {
//                    presentIndex = binding.GOTO.text.toString().toInt()
//                    makeThePaper()
//                }
//                else{
//                   Toast.makeText(this, "Invalid Entry", Toast.LENGTH_SHORT).show()
//
//
//                }
//
//            }
//            else{
//                Toast.makeText(this, "Enter Q.no", Toast.LENGTH_SHORT).show()
//            }
//        }


    }
}
