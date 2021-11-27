const video = document.getElementById("video");

Promise.all([
  faceapi.nets.tinyFaceDetector.loadFromUri("/models"),
  faceapi.nets.faceLandmark68Net.loadFromUri("/models"),
  faceapi.nets.ageGenderNet.loadFromUri("/models"),
]).then(startVideo);

function startVideo() {
  navigator.mediaDevices
    .getUserMedia({ video: true })
    .then(function (stream) {
      video.srcObject = stream;
    })
    .catch(function (err) {
      console.log(err);
    });
}

video.addEventListener("playing", () => {
  
  setInterval(async () => {
	
	const detections = await faceapi
      .detectAllFaces(video, new faceapi.TinyFaceDetectorOptions())
      .withFaceLandmarks()
	  .withAgeAndGender();

	detections.forEach(result=>{
		//나이, 성별 값 받아와 controller로 보내는 함수 호출
		ageGender(result.age, result.gender, result.genderProbability);
	})


  }, 100);
});



var count=0;
var ageList = [];
var genderList = [];
var ProbabilityList = [];
var chooseGender = [];
var totalAge = 0;
var male = 0;
var female = 0;

function ageGender(age,gender,genderProbability){
	
	ageList.push(age);
	genderList.push(gender);
	ProbabilityList.push(genderProbability);
	count++;
	
	if(count>10){
		for(var i=0;i<count;i++){
			totalAge +=ageList[i];
			
			if(ProbabilityList[i]>0.80){
				chooseGender.push(genderList[i]);
			}
		}
		
		var age = totalAge/count;
		
		
		for(var i=0;i<chooseGender.length;i++){
			if(chooseGender[i]=="male")
				male++;
			else
				female++;
		}
		
		if(male>female)
			gender = "male";
		else
			gender = "female";
		
		sendAgeGender(age,gender);
		
	}
}


function sendAgeGender(age,gender){

	$('#age').val(age);
	$('#gender').val(gender);
	
	document.frm.action="/ageGenderFind"; 
	document.frm.method="post"; 
	document.frm.submit();

	
	
	/*location.href="ageGenderFind?age="+age+"&gender="+gender;*/
	reset();
}


function reset(){
	 count=0;
	 ageList = [];
	 genderList = [];
	 ProbabilityList = [];
	 chooseGender = [];
	 totalAge = 0;
	 male = 0;
	 female = 0;
 }