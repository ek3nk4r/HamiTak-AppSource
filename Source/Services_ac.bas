B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=7.3
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals

End Sub

Sub Globals
	Dim txt As String
	Dim txt2 As String
	Private scrollveiw_home As ScrollView
	Private Label11 As Label
	Private ImageView4 As ImageView
	Private Panel4 As Panel
	Private Label50 As Label
	Private Label60 As Label
	Dim cs As Cursor
	Public lbl As Label
	Dim lbl2 As Label
'	Dim lbl3 As Label
	Private lbl_Home As Label
	Dim txt3 As String
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("Services_page")
	txt = File.ReadString(File.DirInternal,"servicename.txt")
	txt2 = File.ReadString(File.DirInternal,"image.txt")
	txt3 = File.ReadString(File.DirInternal,"servicename3.txt")
	Dim r As Reflector
	r.Target = scrollveiw_home
	r.RunMethod2("setVerticalScrollBarEnabled", False, "java.lang.boolean")
	cs = Main.sql.ExecQuery("SELECT * FROM " & txt & " ORDER BY id")
	Dim top As Int = 10
	For i = 0 To cs.RowCount -1
		cs.Position=i
		Dim pnl As Panel
		pnl.Initialize("pnl")
		pnl.LoadLayout("Service_ScrollView")
		pnl.Tag = i
		scrollveiw_home.Panel.AddView(pnl,Panel4.Left+5dip,top,Panel4.Width,Panel4.Height+3dip)
		top = top + Panel4.Height + 7dip
		If pnl.tag = i Then
			Label11.Text = cs.GetString("servicename")
			Label50.Text = Label11.Text
			Label60.Text = cs.GetString("price")
			ImageView4.Bitmap = LoadBitmap(File.DirAssets,txt2)
		End If
		scrollveiw_home.Panel.Height = top + 3dip
		lbl_Home.Text = txt3
	Next
	cs.Close
End Sub

Sub Activity_Resume
	txt = File.ReadString(File.DirInternal,"servicename.txt")
	txt2 = File.ReadString(File.DirInternal,"image.txt")
	txt3 = File.ReadString(File.DirInternal,"servicename3.txt")
	
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	Activity.Finish
End Sub


Sub btn_other_Click
	StartActivity(Profile_ac)
End Sub

Sub btn_home_Click
	StartActivity(Home)
End Sub

Sub btn_profile_Click
	StartActivity(Sefareshat_ac)
End Sub

Sub btm_service_Click
	StartActivity(Service_ac)
End Sub

Sub Label11_Click
	Dim cs1 As Cursor
	lbl = Sender
	cs1 = Main.sql.ExecQuery("SELECT * FROM " & txt & " WHERE servicename = '" & lbl.Text & "'")
	cs1.Position = 0
	lbl2.Initialize("")
	lbl2.Text = cs1.GetString("servicename")
	File.WriteString(File.DirInternal,"servicename2.txt",lbl2.Text)
	StartActivity(Description_ac)
End Sub