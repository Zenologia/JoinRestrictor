# JoinRestrictor

[![Java](https://img.shields.io/badge/Java-16%2B-blue)](https://www.oracle.com/java)  
[![Platform](https://img.shields.io/badge/Platform-Spigot%2FPaper-green)](https://papermc.io/)

## Description  
Restrict who can join your Minecraft server by requiring a specific permission.  
Great for **permission‑whitelisting**, **staff-only test servers**, or **overflow/full‑server routing** with permission‑based exceptions.
---
## Features
- 🔒 Permission‑gated joins — only players with the configured permission may join.  
- 🧰 Fully configurable kick message, permission node, and logging.  
- 🔁 Hot reload via `/joinrestrictor reload`.  
- 🧪 Lightweight & zero‑DB — no stored data, only event handling.
---
## Requirements
- Spigot/Paper (1.17+)  
- Java 16+  
- LuckPerms (or any permissions plugin)
---
## Installation
1. Place the plugin jar in the `plugins` folder.  
2. Start the server to generate the config.  
3. Edit `plugins/JoinRestrictor/config.yml` if desired.  
4. Grant the join permission to allowed players/groups.  
---
## Configuration (`config.yml`)
```yaml
kick-message: "&cYou do not have permission to join this server."
log-denied-joins: true
required-permission: "essentials.joinfullserver"
```
---
### Notes
- Color codes supported (`&c`, `&l`, etc)
- Change `required-permission` to whatever node matches your permission structure
---
## Commands
| Command | Description | Permission |
|--------|-------------|------------|
| `/joinrestrictor reload` | Reload the plugin config | `joinrestrictor.reload` |

---

## Permissions
| Node | Description |
|------|-------------|
| `joinrestrictor.reload` | Allows reloading the config |
| *(configurable)* | Permission required to join |

---

## Examples
**Allow only staff:**  
Set `required-permission: "server.staff"` and grant that to your staff group.

**VIP testing server:**  
Set `required-permission: "server.vip"` and grant to VIP donors.

**Maintenance Mode:**  
Set permission to something only admins have during maintenance.

---

## Troubleshooting
- If all players are kicked → Ensure the required join permission is granted.  
- If color codes show as `&c` → Use `&` formatting, it is supported.  
- If reload doesn't work → Ensure you have `joinrestrictor.reload` permission.

---

## 🧑‍💻 Author

- **Zenologia**
- [GitHub Repository](https://github.com/Zenologia/JoinRestrictor)
- [License](https://github.com/Zenologia/JoinRestrictor/blob/main/LICENSE)
