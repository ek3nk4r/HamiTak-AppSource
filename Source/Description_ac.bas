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
	Dim price As String
	Dim txt2 As String
End Sub

Sub Globals
	Dim txt As String
	Dim txt3 As String
	Dim cs As Cursor
	Private scrollveiw_home As ScrollView
	Private ImageView4 As ImageView
	Private Label50 As Label
	Private Label60 As Label
	Private label_description As Label
	Dim stringutils As StringUtils
	Private lbl_Home As Label
	Dim txt4 As String
	Private btn_sabt As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout("Description_page")
	txt = File.ReadString(File.DirInternal,"servicename.txt")
	txt2 = File.ReadString(File.DirInternal,"image.txt")
	txt3 = File.ReadString(File.DirInternal,"servicename2.txt")
	txt4 = File.ReadString(File.DirInternal,"servicename3.txt")
	cs = Main.sql.ExecQuery("SELECT * FROM " & txt & " WHERE servicename = '" & txt3 & "'")
	cs.Position = 0
	scrollveiw_home.Panel.LoadLayout("Description_label_scrollview")
	label_description.Text = cs.GetString("description")
	label_description.Height = stringutils.MeasureMultilineTextHeight(label_description,label_description.Text)
	scrollveiw_home.Panel.Height = label_description.Height
	ImageView4.Bitmap = LoadBitmap(File.DirAssets,txt2)
	Label50.Text = txt3
	Label60.Text = cs.GetString("price")
	price = cs.GetString("price")
	Dim r As Reflector
	r.Target = scrollveiw_home
	r.RunMethod2("setVerticalScrollBarEnabled", False, "java.lang.boolean")
	lbl_Home.Text = txt4
	
End Sub

Sub Activity_Resume
	txt = File.ReadString(File.DirInternal,"servicename.txt")
	txt2 = File.ReadString(File.DirInternal,"image.txt")
	txt3 = File.ReadString(File.DirInternal,"servicename2.txt")
	txt4 = File.ReadString(File.DirInternal,"servicename3.txt")
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

Sub btn_sabt_Click
	StartActivity(Sefaresh_ac)
End Sub