const express = require('express');
const router = express.Router();
const { getAdmins, getGraphs, alertUser, addUser, getUsers, updateUser, deleteUser } = require('../controllers/adminController');

router.get('/admin', getAdmins);
router.get('/graphs', getGraphs);
router.post('/alert', alertUser);
router.post('/add/user', addUser);
router.get('/user', getUsers);
router.put('/user/:id', updateUser);
router.delete('/user/delete/:id', deleteUser);

module.exports = router;
