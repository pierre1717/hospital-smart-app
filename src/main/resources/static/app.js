const api = '/api';

async function fetchData(url) {
  const res = await fetch(url);
  return await res.json();
}

async function refreshDoctors() {
  const data = await fetchData(api+'/doctors');
  const table = document.getElementById('doctors');
  table.innerHTML = '<tr><th>ID</th><th>Nom</th><th>Spécialité</th></tr>';
  data.forEach(d => table.innerHTML += `<tr><td>${d.id}</td><td>${d.name}</td><td>${d.specialization}</td></tr>`);
  const sel = document.getElementById('app-doctor');
  sel.innerHTML = data.map(d => `<option value="${d.id}">${d.name}</option>`).join('');
}

async function refreshPatients() {
  const data = await fetchData(api+'/patients');
  const table = document.getElementById('patients');
  table.innerHTML = '<tr><th>ID</th><th>Nom</th><th>Âge</th><th>Sexe</th></tr>';
  data.forEach(p => table.innerHTML += `<tr><td>${p.id}</td><td>${p.name}</td><td>${p.age}</td><td>${p.gender}</td></tr>`);
  const sel = document.getElementById('app-patient');
  sel.innerHTML = data.map(p => `<option value="${p.id}">${p.name}</option>`).join('');
}

async function refreshAppointments() {
  const data = await fetchData(api+'/appointments');
  const table = document.getElementById('appointments');
  table.innerHTML = '<tr><th>ID</th><th>Patient</th><th>Docteur</th><th>Date</th><th>Symptômes</th><th>Diagnostic</th></tr>';
  data.forEach(a => table.innerHTML += `<tr><td>${a.id}</td><td>${a.patientId}</td><td>${a.doctorId}</td><td>${a.date}</td><td>${a.symptoms}</td><td>${a.diagnosis}</td></tr>`);
}

async function addDoctor() {
  const name = document.getElementById('doctor-name').value;
  const spec = document.getElementById('doctor-spec').value;
  await fetch(api+'/doctors', {method:'POST', headers:{'Content-Type':'application/json'}, body:JSON.stringify({name, specialization:spec})});
  refreshDoctors();
}

async function addPatient() {
  const name = document.getElementById('patient-name').value;
  const age = parseInt(document.getElementById('patient-age').value);
  const gender = document.getElementById('patient-gender').value;
  await fetch(api+'/patients', {method:'POST', headers:{'Content-Type':'application/json'}, body:JSON.stringify({name, age, gender})});
  refreshPatients();
}

async function addAppointment() {
  const patientId = parseInt(document.getElementById('app-patient').value);
  const doctorId = parseInt(document.getElementById('app-doctor').value);
  const date = document.getElementById('app-date').value;
  const symptoms = document.getElementById('app-symptoms').value;
  await fetch(api+'/appointments', {method:'POST', headers:{'Content-Type':'application/json'}, body:JSON.stringify({patientId, doctorId, date, symptoms})});
  refreshAppointments();
}

refreshDoctors(); refreshPatients(); refreshAppointments();
